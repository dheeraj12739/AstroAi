package com.astro.ai.astroai.libraryEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LibraryEvent {

    private Integer eventId;

    private LibraryEventType eventType;

    private Book book;
}
