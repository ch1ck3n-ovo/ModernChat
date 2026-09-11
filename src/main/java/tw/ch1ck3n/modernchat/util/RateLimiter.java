package tw.ch1ck3n.modernchat.util;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class RateLimiter {
    private final ConcurrentHashMap<UUID, Long> limitedMap;
    private final boolean enabled;
    private final int cooldownMs;
    private final Component warningMessage;

    public RateLimiter(final ConfigGetter config) {
        this.enabled = config.chatCooldownEnable;
        this.cooldownMs = config.chatCooldownTimeMs;
        this.warningMessage = config.chatCooldownMessage;
        this.limitedMap = new ConcurrentHashMap<>();
    }

    public boolean allowed(final Player player) {
        if (!enabled || cooldownMs <= 0) return true;

        long now = System.currentTimeMillis();
        long lastMsgTime = limitedMap.getOrDefault(player.getUniqueId(), 0L);

        if (now - lastMsgTime < cooldownMs) {
            if (!warningMessage.equals(Component.empty())) player.sendMessage(warningMessage);
            return false;
        }

        limitedMap.put(player.getUniqueId(), now);
        return true;
    }
}
