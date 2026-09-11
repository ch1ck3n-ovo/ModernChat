package tw.ch1ck3n.modernchat.util;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Set;

public final class RadiusFilter {
    private final boolean enabled;
    private final double radius;

    public RadiusFilter(final ConfigGetter config) {
        this.enabled = config.chatRadiusEnable;
        this.radius = config.chatRadiusDistance * config.chatRadiusDistance;
    }

    public void filter(
            final AsyncChatEvent event,
            final Set<Player> mentioned,
            final Component finalMessage,
            final boolean isGlobal
    ) {
        Player sender = event.getPlayer();
        Location location = sender.getLocation();
        Set<Audience> viewers = event.viewers();

        viewers.removeIf(audience -> {
            if (audience instanceof Player target) {
                if (!isGlobal && enabled && !mentioned.contains(target)) {
                    if (!target.getWorld().equals(location.getWorld())) return true;
                    return target.getLocation().distanceSquared(location) > radius;
                }
            }
            return false;
        });

        for (Audience viewer : event.viewers()) {
            Component message = finalMessage;

            if (viewer instanceof Player target && mentioned.contains(target)) {
                target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.2f);
                message = finalMessage.replaceText(TextReplacementConfig.builder()
                        .matchLiteral("@" + target.getName())
                        .replacement(Component.text("@" + target.getName(), NamedTextColor.YELLOW, TextDecoration.BOLD))
                        .build());
            }
            viewer.sendMessage(message);
        }
    }
}
