package io.github.cepr0.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.cepr0.demo.support.Cuid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.Instant;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "models")
public class Model {

	@Id
	@Column(columnDefinition = "text")
	private String id;
	
	@Column(nullable = false)
	@JsonFormat(shape = STRING)
	private Instant createdAt;
	
	@Column(nullable = false, columnDefinition = "text")
	private String tenant;
	
	public Model(String tenant) {
		this.tenant = tenant;
	}
	
	@PrePersist
	protected void prePersist() {
		id = Cuid.createCuid();
		createdAt = Instant.now();
	}

}
