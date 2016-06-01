package io.github.jisaacs1207.followers;
import org.bukkit.ChatColor;

public class Message {
    private StringBuilder _builder;
    Message() {
        this._builder = new StringBuilder();
    }
    public Message newLine() {
        this._builder.append("\n");
        return this;
    }
    public Message append(ChatColor color, String text) {
        this._builder.append(color).append(text);
        return this;
    }
    public Message aqua(String text) {
        return this.append(ChatColor.AQUA, text);
    }
    public Message black(String text) {
        return this.append(ChatColor.BLACK, text);
    }
    public Message blue(String text) {
        return this.append(ChatColor.BLUE, text);
    }
    public Message darkaqua(String text) {
        return this.append(ChatColor.DARK_AQUA, text);
    }
    public Message darkblue(String text) {
        return this.append(ChatColor.DARK_BLUE, text);
    }
    public Message darkgray(String text) {
        return this.append(ChatColor.DARK_GRAY, text);
    }
    public Message darkgreen(String text) {
        return this.append(ChatColor.DARK_GREEN, text);
    }
    public Message darkpurple(String text) {
        return this.append(ChatColor.DARK_PURPLE, text);
    }
    public Message darkred(String text) {
        return this.append(ChatColor.DARK_RED, text);
    }
    public Message gold(String text) {
        return this.append(ChatColor.GOLD, text);
    }
    public Message gray(String text) {
        return this.append(ChatColor.GRAY, text);
    }
    public Message green(String text) {
        return this.append(ChatColor.GREEN, text);
    }
    public Message lightpurple(String text) {
        return this.append(ChatColor.LIGHT_PURPLE, text);
    }
    public Message red(String text) {
        return this.append(ChatColor.RED, text);
    }
    public Message white(String text) {
        return this.append(ChatColor.WHITE, text);
    }
    public Message yellow(String text) {
        return this.append(ChatColor.YELLOW, text);
    }
    public String toString() {
        return this._builder.toString();
    }
    public String end() {
        return this.toString();
    }
    public Message rainbowWords(String... words) {
        int readcount = 0;
        ChatColor[] colors = ChatColor.values();
        while (readcount < words.length) {
            for (int i = 0;i < colors.length;i++) {
                this._builder.append(colors[i]).append(words[readcount]);
                readcount += 1;
                if (readcount == words.length) break;
            }
        }
        return this;
    }
    public Message rainbow(String text) {
        int readcount = 0;
        ChatColor[] colors = ChatColor.values();
        while (readcount < text.length()) {
            for (int i = 0;i < colors.length;i++) {
                this._builder.append(colors[i]).append(text.charAt(readcount));
                readcount += 1;
                if (readcount == text.length()) break;
            }
        }
        return this;
    }
}
