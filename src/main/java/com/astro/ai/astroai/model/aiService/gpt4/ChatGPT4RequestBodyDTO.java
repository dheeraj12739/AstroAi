package com.astro.ai.astroai.model.aiService.gpt4;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class ChatGPT4RequestBodyDTO {

    @SerializedName("prompt")
    private String prompt;

    @SerializedName("max_tokens")
    private long maxTokens = 150;

    public ChatGPT4RequestBodyDTO() {
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public long getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(long maxTokens) {
        this.maxTokens = maxTokens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatGPT4RequestBodyDTO that = (ChatGPT4RequestBodyDTO) o;
        return maxTokens == that.maxTokens && Objects.equals(prompt, that.prompt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prompt, maxTokens);
    }

    @Override
    public String toString() {
        return "ChatGPT4RequestBodyDTO{" +
                "prompt='" + prompt + '\'' +
                ", maxTokens=" + maxTokens +
                '}';
    }
}
