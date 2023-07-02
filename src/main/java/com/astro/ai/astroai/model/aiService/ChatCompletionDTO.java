package com.astro.ai.astroai.model.aiService;

public class ChatCompletionDTO {

    private String id;
    private String object;
    private long created;
    private String model;
    private ChoiceDTO[] choices;
    private UsageDTO usage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ChoiceDTO[] getChoices() {
        return choices;
    }

    public void setChoices(ChoiceDTO[] choices) {
        this.choices = choices;
    }

    public UsageDTO getUsage() {
        return usage;
    }

    public void setUsage(UsageDTO usage) {
        this.usage = usage;
    }
}
