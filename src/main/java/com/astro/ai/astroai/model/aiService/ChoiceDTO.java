package com.astro.ai.astroai.model.aiService;

public class ChoiceDTO {

    private int index;
    private MessageDTO message;
    private String finishReason;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public MessageDTO getMessage() {
        return message;
    }

    public void setMessage(MessageDTO message) {
        this.message = message;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }
}
