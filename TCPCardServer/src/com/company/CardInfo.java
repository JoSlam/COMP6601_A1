package com.company;

public class CardInfo {

    private String message;
    private String cardName;
    private CardImage cardImage;
    private Integer cardCount;

    public CardInfo() {
    }

    public CardInfo(String cardName, Integer cardCount) {
        this.message = "";
        this.cardCount = cardCount;
        this.cardName = cardName;
    }

    public void formatMessage(String message) {
        this.message = message + this.cardCount.toString();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CardImage getCardImage() {
        return cardImage;
    }

    public void setCardImage(CardImage cardImage) {
        this.cardImage = cardImage;
    }

    public Integer getCardCount() {
        return cardCount;
    }

    public void setCardCount(Integer cardCount) {
        this.cardCount = cardCount;
    }

    public void incrementCardCount() {
        this.cardCount++;
    }

    public void decrementCardCount() {
        this.cardCount--;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
