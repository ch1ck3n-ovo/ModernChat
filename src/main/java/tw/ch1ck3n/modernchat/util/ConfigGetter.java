package tw.ch1ck3n.modernchat.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.ClickType;
import tw.ch1ck3n.modernchat.ModernChat;

import java.util.List;
import java.util.regex.Pattern;

public final class ConfigGetter {
    private final MiniMessage miniMessage;

    public Component chatFormatGlobalPrefix;
    public Component chatFormatLocalPrefix;
    public Component chatFormatSeparateChar;

    public boolean chatFilterEnable;
    public String chatFilterReplacementTag;
    public Pattern chatFilterPatterns;

    public boolean chatRadiusEnable;
    public int chatRadiusDistance;

    public boolean chatItemEnable;
    public boolean chatMentionEnable;

    public boolean chatCooldownEnable;
    public int chatCooldownTimeMs;
    public Component chatCooldownMessage;

    public boolean clickableNameEnable;
    public String clickableNameHoverText;
    public String clickableNameSuggestCommand;

    public boolean chatIgnoreEnable;

    public ConfigGetter() {
        this.miniMessage = MiniMessage.miniMessage();
        this.load();
    }

    private void load() {
        ModernChat.getInstance().reloadConfig();
        FileConfiguration config = ModernChat.getInstance().getConfig();

        chatFormatGlobalPrefix = miniMessage.deserialize(config.getString("chat-format.global-prefix", ""));
        chatFormatLocalPrefix = miniMessage.deserialize(config.getString("chat-format.local-prefix", ""));
        chatFormatSeparateChar = miniMessage.deserialize(config.getString("chat-format.separate-char", ""));

        chatFilterEnable = config.getBoolean("chat-filter.enable", true);
        chatFilterReplacementTag = config.getString("chat-filter.replacement-tag", "*");

        List<String> patterns = config.getStringList("chat-filter.patterns");
        if (patterns.isEmpty() || !chatFilterEnable) chatFilterPatterns = Pattern.compile("(?!)");
        else chatFilterPatterns = Pattern.compile("(?i)(" + String.join("|", patterns) + ")");

        chatRadiusEnable = config.getBoolean("chat-radius.enable", true);
        chatRadiusDistance = config.getInt("chat-radius.distance", 100);

        chatItemEnable = config.getBoolean("chat-item.enable", true);
        chatMentionEnable = config.getBoolean("chat-mention.enable", true);

        chatCooldownEnable = config.getBoolean("chat-cooldown.enable", true);
        chatCooldownTimeMs = config.getInt("chat-cooldown.time-ms", 1000);
        chatCooldownMessage = miniMessage.deserialize(config.getString("chat-cooldown.message", ""));

        clickableNameEnable = config.getBoolean("clickable-name.enable", true);
        clickableNameHoverText = config.getString("clickable-name.hover-text", "");
        clickableNameSuggestCommand = config.getString("clickable-name.suggest-command", "");

        chatIgnoreEnable = config.getBoolean("chat-ignore.enable", true);
    }
}
