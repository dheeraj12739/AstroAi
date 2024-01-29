package com.astro.ai.astroai.libraryEvent;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book {

    private Long bookId;

    private String bookName;

    private String authorName;

}
