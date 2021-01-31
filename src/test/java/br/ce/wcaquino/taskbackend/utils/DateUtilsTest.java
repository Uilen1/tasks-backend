package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {
	
	@Test
	public void deveRetornarTrueParaDatasFuturas() {
		LocalDate date = LocalDate.of(2030, 12, 25);
		Assert.assertTrue("Validando retorno do metodo para datas futuras", DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void deveRetornarFalseParaDatasPassadas() {
		LocalDate date = LocalDate.of(2010, 12, 25);
		Assert.assertFalse("Validando retorno do metodo para datas Passadas", DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void deveRetornarTrueParaDatasPresente() {
		LocalDate date = LocalDate.now();
		Assert.assertTrue("Validando retorno do metodo para data presente", DateUtils.isEqualOrFutureDate(date));
	}

}
