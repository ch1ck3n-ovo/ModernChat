package tw.ch1ck3n.modernchat.chat;

import tw.ch1ck3n.modernchat.util.ConfigGetter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ChatFilter {
    private final boolean enabled;
    private final String replacementTag;
    private final Pattern patterns;

    public ChatFilter(final ConfigGetter config) {
        this.enabled = config.chatFilterEnable;
        this.replacementTag = config.chatFilterReplacementTag;
        this.patterns = config.chatFilterPatterns;
    }

    public String filter(final String input) {
        if (!enabled) return input;

        Matcher matcher = patterns.matcher(input);
        if (!matcher.find()) return input;
        matcher.reset();

        return matcher.replaceAll(result -> {
            int length = result.group().length();
            return replacementTag.repeat(length);
        });
    }
}
