package tw.ch1ck3n.modernchat.listener;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import tw.ch1ck3n.modernchat.chat.ChatFilter;
import tw.ch1ck3n.modernchat.chat.ChatRenderer;
import tw.ch1ck3n.modernchat.parser.ItemParser;
import tw.ch1ck3n.modernchat.parser.MentionParser;
import tw.ch1ck3n.modernchat.chat.ChatResolver;
import tw.ch1ck3n.modernchat.util.*;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public final class AsyncChatListener implements Listener {
    private final MiniMessage miniMessage;

    private final ChatRenderer chatRenderer;
    private final ChatFilter chatFilter;
    private final ChatResolver chatResolver;
    private final MentionParser mentionParser;
    private final ItemParser itemParser;
    private final RadiusFilter radiusFilter;

    private final RateLimiter rateLimiter;
    private final ClickableName clickableName;

    public AsyncChatListener(final ConfigGetter config) {
        this.chatRenderer = new ChatRenderer(config);
        this.chatFilter = new ChatFilter(config);
        this.chatResolver = new ChatResolver();
        this.mentionParser = new MentionParser(config);
        this.itemParser = new ItemParser(config);

        this.rateLimiter = new RateLimiter(config);
        this.radiusFilter = new RadiusFilter(config);
        this.clickableName = new ClickableName(config);

        this.miniMessage = MiniMessage.miniMessage();
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAsyncChat(final AsyncChatEvent event) {
        event.setCancelled(true);

        Player sender = event.getPlayer();

        if (!rateLimiter.allowed(sender)) return;

        TagResolver.Builder builder = TagResolver.builder();
        builder.resolver(chatResolver.build());

        String tempText = PlainTextComponentSerializer.plainText().serialize(event.message());

        boolean isGlobal = false;
        if (tempText.toLowerCase().startsWith("#all ")) {
            isGlobal = true;
            tempText = tempText.substring(4).trim();
        }

        Set<Player> mentioned = new HashSet<>();
        tempText = mentionParser.parse(tempText, mentioned);
        tempText = itemParser.parse(tempText, sender, builder);

        tempText = chatFilter.filter(tempText);

        Component processedText = miniMessage.deserialize(tempText, builder.build());

        Component interactiveName = clickableName.format(sender, sender.displayName());

        Component finalMessage = chatRenderer.build(isGlobal).render(
                sender,
                interactiveName,
                processedText,
                Audience.empty()
        );

        radiusFilter.filter(event, mentioned, finalMessage, isGlobal);
    }
}
