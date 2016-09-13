/**
 * Token.java
 * Class for Side's Tokens
 * @author eccarrilloe
 */

public class Token implements Constants {
  public int color;

  public Token(Token other) {
    this.color = other.color;
  }

  public Token(int color) {
    this.color = color;
  }

  @Override
  public String toString() {
    char tokenColor = C_WHITE;
    switch (this.color) {
      case WHITE:  tokenColor = C_WHITE;  break;
      case GREEN:  tokenColor = C_GREEN;  break;
      case BLUE:   tokenColor = C_BLUE;   break;
      case RED:    tokenColor = C_RED;    break;
      case ORANGE: tokenColor = C_ORANGE; break;
      case YELLOW: tokenColor = C_YELLOW; break;
      default: break;
    }
    return String.valueOf(tokenColor);
  }
}
