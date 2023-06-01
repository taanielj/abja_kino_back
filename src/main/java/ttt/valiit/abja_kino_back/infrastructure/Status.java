package ttt.valiit.abja_kino_back.infrastructure;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE("A"), DELETED("D");
    private final String letter;
    Status(String letter) {
        this.letter = letter;
    }

}
