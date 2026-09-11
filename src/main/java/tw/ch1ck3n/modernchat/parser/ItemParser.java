package tw.ch1ck3n.modernchat.parser;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import tw.ch1ck3n.modernchat.util.ConfigGetter;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ItemParser {
    private final boolean enabled;
    private final Pattern itemPattern;

    public ItemParser(final ConfigGetter config) {
        this.enabled = config.chatItemEnable;
        this.itemPattern = Pattern.compile("(?i)\\[item]");
    }

    public String parse(final String text, final Player sender, final TagResolver.Builder builder) {
        if (!enabled) return text;

        Matcher matcher = itemPattern.matcher(text);
        if (!matcher.find()) return text;

        matcher.reset();
        return matcher.replaceAll((MatchResult result) -> {
            ItemStack handItem = sender.getInventory().getItemInMainHand();

            if (handItem.isEmpty())
                return result.group();

            Component component = Component.text("")
                    .append(handItem.displayName())
                    .color(NamedTextColor.AQUA)
                    .hoverEvent(handItem.asHoverEvent());

            builder.resolver(Placeholder.component("item", component));
            return "<item>";
        });
    }
}
