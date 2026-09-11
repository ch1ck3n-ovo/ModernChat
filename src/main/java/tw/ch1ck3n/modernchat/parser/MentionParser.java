package tw.ch1ck3n.modernchat.parser;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tw.ch1ck3n.modernchat.util.ConfigGetter;

import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MentionParser {
    private final boolean enabled;
    private final Pattern mentionPattern;

    public MentionParser(final ConfigGetter config) {
        this.enabled = config.chatMentionEnable;
        this.mentionPattern = Pattern.compile("(?i)@([a-zA-Z0-9_]{2,16})");
    }

    public String parse(final String text, final Set<Player> mentioned) {
        if (!enabled) return text;

        Matcher matcher = mentionPattern.matcher(text);
        if (!matcher.find()) return text;

        matcher.reset();
        return matcher.replaceAll((MatchResult result) -> {
            String name = result.group(1);
            Player target = Bukkit.getPlayerExact(name);

            if (target != null && target.isOnline()) {
                mentioned.add(target);
//                return "<yellow><bold>@" + name + "</bold></yellow>";
            }
            return result.group();
        });
    }
}
