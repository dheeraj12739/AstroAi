package com.astro.ai.astroai.standards.repository;

import com.astro.ai.astroai.standards.entity.ChatGPTCredentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatGPTCredentialsRepository extends CrudRepository<ChatGPTCredentials, Long> {

    List<ChatGPTCredentials> findChatGPTCredentialsByActive(boolean isActive);
}
