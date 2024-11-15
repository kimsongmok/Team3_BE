package com.splanet.splanet.gpt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.splanet.splanet.core.properties.GptProperties;
import com.splanet.splanet.plan.dto.PlanResponseDto;
<<<<<<< HEAD
=======
import com.splanet.splanet.plan.dto.PlanTimeDto;
>>>>>>> weekly/11
import com.splanet.splanet.plan.service.PlanService;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
<<<<<<< HEAD
=======
import java.util.Map;
>>>>>>> weekly/11

@Service
public class GptService {

    private final OpenAiApi openAiApi;
    private final GptProperties gptProperties;
    private final PlanService planService;
    private final ObjectMapper objectMapper;

    private static final double RESPONSE_TEMPERATURE = 0.8;
<<<<<<< HEAD
    private static final String PROMPT_TEMPLATE_STRONG =
            "사용자 입력: \"%s\" (deviceId: %s) (groupId: %s) 기존 일정이 있다면 해당 시간과는 겹치지 않게 해 줘 기존일정:%s 현재 시간 이후로 가능한 자주 반복하여 짧고 집중적으로 일정을 완수할 수 있도록 계획을 세워줘. 시험이 포함된 경우, 시험 당일이 아닌 전날까지 준비가 완료되도록 해줘 (%s 기준). 모든 일정은 한국 시간(UTC+9)을 기준으로 설정해줘.";
    private static final String PROMPT_TEMPLATE_MODERATE =
            "사용자 입력: \"%s\" (deviceId: %s) (groupId: %s) 기존 일정이 있다면 해당 시간과는 겹치지 않게 해 줘 기존일정:%s 현재 시간 이후로 적당한 간격을 두고 모든 일정을 완수할 수 있도록 계획해줘. 시험이 포함된 경우, 시험 당일이 아닌 전날까지 준비가 완료되도록 해줘 (%s 기준). 모든 일정은 한국 시간(UTC+9)을 기준으로 설정해줘.";
    private static final String PROMPT_TEMPLATE_LIGHT =
            "사용자 입력: \"%s\" (deviceId: %s) (groupId: %s) 기존 일정이 있다면 해당 시간과는 겹치지 않게 해 줘 기존일정:%s 현재 시간 이후로 여유 있게 모든 일정을 완수할 수 있도록 계획해줘. 시험이 포함된 경우, 시험 당일이 아닌 전날까지 준비가 완료되도록 해줘 (%s 기준). 모든 일정은 한국 시간(UTC+9)을 기준으로 설정해줘.";
=======
    private static final Map<Integer, String> PROMPT_TEMPLATES = Map.of(
            3, "사용자 입력: \"%s\" (deviceId: %s) (groupId: %s) 제공된 가이드라인을 따르지 않으면 페널티가 부과될 것입니다. 모든 지침을 주의깊게 읽고 그에 따라 행동하세요. 기존 startDate와 endDate사이에 일정을 생성하지 말아줘. 기존 startDate, endDate:%s 현재 시간 이후로 가능한 자주 반복하여 짧고 집중적으로 일정을 완수할 수 있도록 계획을 세워줘. 현재 시간 : (%s 기준). 모든 일정은 한국 시간(UTC+9)을 기준으로 설정해줘. 또한, 기존 일정을 생각하고 새로운 일정을 적당한 간격을 두고 배치해줘.",
            2, "사용자 입력: \"%s\" (deviceId: %s) (groupId: %s) 제공된 가이드라인을 따르지 않으면 페널티가 부과될 것입니다. 모든 지침을 주의깊게 읽고 그에 따라 행동하세요. 기존 startDate와 endDate사이에 일정을 생성하지 말아줘. 기존 startDate, endDate:%s 현재 시간 이후로 적당한 간격을 두고 모든 일정을 완수할 수 있도록 계획해줘. 현재 시간 : (%s 기준). 모든 일정은 한국 시간(UTC+9)을 기준으로 설정해줘. 또한, 기존 일정을 생각하고 새로운 일정을 적당한 간격을 두고 배치해줘.",
            1, "사용자 입력: \"%s\" (deviceId: %s) (groupId: %s) 제공된 가이드라인을 따르지 않으면 페널티가 부과될 것입니다. 모든 지침을 주의깊게 읽고 그에 따라 행동하세요. 기존 startDate와 endDate사이에 일정을 생성하지 말아줘. 기존 startDate, endDate:%s 현재 시간 이후로 여유 있게 모든 일정을 완수할 수 있도록 계획해줘. 현재 시간 : (%s 기준). 모든 일정은 한국 시간(UTC+9)을 기준으로 설정해줘. 또한, 기존 일정을 생각하고 새로운 일정을 적당한 간격을 두고 배치해줘."
    );
>>>>>>> weekly/11

    public GptService(OpenAiApi openAiApi, GptProperties gptProperties, PlanService planService, ObjectMapper objectMapper) {
        this.openAiApi = openAiApi;
        this.gptProperties = gptProperties;
        this.planService = planService;
        this.objectMapper = objectMapper;
    }

<<<<<<< HEAD
    public String generateResponseForStrong(String userInput, Long userId, String deviceId) {
        return generateResponse(userInput, userId, deviceId, PROMPT_TEMPLATE_STRONG, 3);
    }

    public String generateResponseForModerate(String userInput, Long userId, String deviceId) {
        return generateResponse(userInput, userId, deviceId, PROMPT_TEMPLATE_MODERATE, 2);
    }

    public String generateResponseForLight(String userInput, Long userId, String deviceId) {
        return generateResponse(userInput, userId, deviceId, PROMPT_TEMPLATE_LIGHT, 1);
    }

    private String generateResponse(String userInput, Long userId, String deviceId, String promptTemplate, int groupId) {
        String currentTime = getCurrentTime();
        List<PlanResponseDto> futurePlans = (userId != null) ? planService.getAllFuturePlansByUserId(userId) : List.of();
        String planJson = convertPlansToJson(futurePlans);
        String fullPrompt = String.format(promptTemplate, userInput, deviceId, groupId, planJson, currentTime);
        System.out.println(fullPrompt);
=======
    public String generateResponse(String userInput, Long userId, String deviceId, int groupId) {
        String currentTime = getCurrentTime();
        List<PlanTimeDto> futurePlans = (userId != null) ? planService.getAllFuturePlanTimesByUserId(userId) : List.of();
        String planJson = convertPlansToJson(futurePlans);
        String promptTemplate = PROMPT_TEMPLATES.get(groupId);
        String fullPrompt = String.format(promptTemplate, userInput, deviceId, groupId, planJson, currentTime);
>>>>>>> weekly/11

        OpenAiApi.ChatCompletionMessage userMessage = createUserMessage(fullPrompt);
        OpenAiApi.ChatCompletionRequest chatRequest = createChatRequest(userMessage);

        return getGptResponse(chatRequest);
    }

    private String getCurrentTime() {
        return LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

<<<<<<< HEAD
    private String convertPlansToJson(List<PlanResponseDto> futurePlans) {
=======
    private String convertPlansToJson(List<PlanTimeDto> futurePlans) {
>>>>>>> weekly/11
        try {
            return objectMapper.writeValueAsString(futurePlans);
        } catch (JsonProcessingException e) {
            return "[]";
        }
    }

    private OpenAiApi.ChatCompletionMessage createUserMessage(String fullPrompt) {
        return new OpenAiApi.ChatCompletionMessage(fullPrompt, OpenAiApi.ChatCompletionMessage.Role.USER);
    }

    private OpenAiApi.ChatCompletionRequest createChatRequest(OpenAiApi.ChatCompletionMessage userMessage) {
        return new OpenAiApi.ChatCompletionRequest(
                List.of(userMessage),
                gptProperties.getGptModel(),
                RESPONSE_TEMPERATURE
        );
    }

    private String getGptResponse(OpenAiApi.ChatCompletionRequest chatRequest) {
        ResponseEntity<OpenAiApi.ChatCompletion> responseEntity = openAiApi.chatCompletionEntity(chatRequest);
        OpenAiApi.ChatCompletion chatCompletion = responseEntity.getBody();

        if (chatCompletion != null && !chatCompletion.choices().isEmpty()) {
            return chatCompletion.choices().get(0).message().content();
        } else {
            return "응답이 유효하지 않습니다.";
        }
    }
}
<<<<<<< HEAD
=======

>>>>>>> weekly/11
