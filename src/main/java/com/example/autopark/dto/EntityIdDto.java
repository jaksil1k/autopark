package com.example.autopark.dto;

import com.example.autopark.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EntityIdDto {

    @JsonProperty("id")
    private UUID id;

    public static EntityIdDto fromBaseEntity(BaseEntity entity) {
        return EntityIdDto.builder()
                .id(entity.getId())
                .build();
    }
}
