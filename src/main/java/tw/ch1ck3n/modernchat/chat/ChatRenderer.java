package tw.ch1ck3n.modernchat.chat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import tw.ch1ck3n.modernchat.util.ConfigGetter;

public final class ChatRenderer {
    private final Component globalPrefix;
    private final Component localPrefix;
    private final Component separateChar;

    public ChatRenderer(final ConfigGetter config) {
        this.globalPrefix = config.chatFormatGlobalPrefix;
        this.localPrefix = config.chatFormatLocalPrefix;
        this.separateChar = config.chatFormatSeparateChar;
    }

    public io.papermc.paper.chat.ChatRenderer build(final boolean isGlobal) {
        final Component activePrefix = isGlobal ? this.globalPrefix : this.localPrefix;
        return (source, sourceDisplayName, message, viewer) ->
                Component.text()
                        .append(activePrefix)
                        .append(sourceDisplayName)
                        .append(separateChar)
                        .append(message)
                        .build();
    }
}
