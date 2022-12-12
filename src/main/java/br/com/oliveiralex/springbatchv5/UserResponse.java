package br.com.oliveiralex.springbatchv5;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserResponse {
	private String id;
	private String login;
	private String name;
	private String location;
	private String company;
	private Integer followers;
	private Integer following;
	private Instant created_at;
}
