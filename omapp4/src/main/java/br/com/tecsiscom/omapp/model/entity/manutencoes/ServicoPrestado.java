package br.com.tecsiscom.omapp.model.entity.manutencoes;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.tecsiscom.omapp.model.entity.servicos.Servico;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class ServicoPrestado extends Despesa{

	//@JsonFormat(pattern = "dd/MM/yyyy")
	//@Temporal(TemporalType.DATE)
	//@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/yyyy")
	//private Date dataEntrega;
	private LocalDateTime dataEntrega;
	
	//@OneToOne( cascade = CascadeType.ALL)
	//@JoinColumn(name="servico_id",referencedColumnName = "id")
	@ManyToOne
	private Servico servico;
	
}
