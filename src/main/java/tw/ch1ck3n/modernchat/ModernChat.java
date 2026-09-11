package tw.ch1ck3n.modernchat;

import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;
import tw.ch1ck3n.modernchat.chat.ChatFilter;
import tw.ch1ck3n.modernchat.chat.ChatRenderer;
import tw.ch1ck3n.modernchat.listener.AsyncChatListener;
import tw.ch1ck3n.modernchat.parser.ItemParser;
import tw.ch1ck3n.modernchat.parser.MentionParser;
import tw.ch1ck3n.modernchat.chat.ChatResolver;
import tw.ch1ck3n.modernchat.util.ConfigGetter;
import tw.ch1ck3n.modernchat.util.RadiusFilter;

import java.util.regex.Pattern;

public final class ModernChat extends JavaPlugin {
    @Getter
    private static ModernChat instance;
    private ConfigGetter config;

    @Override
    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();
        this.config = new ConfigGetter();

        AsyncChatListener chatListener = new AsyncChatListener(this.config);
        getServer().getPluginManager().registerEvents(chatListener, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
