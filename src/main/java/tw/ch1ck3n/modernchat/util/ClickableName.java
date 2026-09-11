package tw.ch1ck3n.modernchat.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public final class ClickableName {
    private final boolean enabled;
    private final String hoverText;
    private final String suggestCommand;
    private final MiniMessage miniMessage;

    public ClickableName(final ConfigGetter config) {
        this.enabled = config.clickableNameEnable;
        this.hoverText = config.clickableNameHoverText;
        this.suggestCommand = config.clickableNameSuggestCommand;
        this.miniMessage = MiniMessage.miniMessage();
    }

    public Component format(final Player source, final Component originalName) {
        if (!enabled) return originalName;

        String playerName = source.getName();
        String cmd = suggestCommand.replace("%player%", playerName);
        Component hoverComp = miniMessage.deserialize(hoverText.replace("%player%", playerName));

        return originalName
                .hoverEvent(HoverEvent.showText(hoverComp))
                .clickEvent(ClickEvent.suggestCommand(cmd));
    }
}