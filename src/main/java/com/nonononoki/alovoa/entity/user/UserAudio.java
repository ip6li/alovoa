package com.nonononoki.alovoa.entity.user;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nonononoki.alovoa.entity.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude="user")
public class UserAudio {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique=true)
	private UUID uuid;
	
	@JsonIgnore
	@OneToOne
	private User user;

	@Deprecated
	@Column(columnDefinition = "mediumtext")
	private String data;

	@Lob
	@Column(length=10000000)
	private byte[] bin;

}
