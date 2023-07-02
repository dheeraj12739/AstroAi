package com.astro.ai.astroai.model.aiService;

import java.util.Objects;

public class AiServiceMessageDTO {

    private String role = "user";

    private String content;

    public AiServiceMessageDTO() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AiServiceMessageDTO that = (AiServiceMessageDTO) o;
        return Objects.equals(role, that.role) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, content);
    }

    @Override
    public String toString() {
        return "AiServiceMessageDTO{" +
                "role='" + role + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
