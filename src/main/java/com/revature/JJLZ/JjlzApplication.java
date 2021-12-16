package com.revature.JJLZ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper.Builder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

@SpringBootApplication
public class JjlzApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(JjlzApplication.class, args);
//		User me = new User();
//		me.setUsername("me");
//		me.setPassword("me");
//		Scanner sc = new Scanner(System.in);
//		if (sc.nextLine() == "1"){
//			UserService userService = null;
//			System.out.println(userService.totalBalance(me));
/*		ZoneOffset zone = ZoneOffset.of("Z");
		LocalDateTime d3 = LocalDateTime.parse("2020-12-10T00:00:00.00");
		LocalDateTime d4 = LocalDateTime.parse("2021-12-10T00:00:00.0");
//		LocalDateTime d1 = LocalDateTime.of(2020,12,10,19,10);
//		System.out.println(d3.toEpochSecond(zone)+"\n" + d4.toEpochSecond(zone));
		long sec = d3.toEpochSecond(zone);
//		LocalDateTime d2 = LocalDateTime.of(2021,12,10,19,10);
		long sec2 = d4.toEpochSecond(zone);
		String interval = "1d";
		String symb = "^IXIC";

		String dq = String.format("https://query1.finance.yahoo.com/v7/finance/download/%s?period1=%d&period2=%d&interval=%s&events=history&includeAdjustedClose=true",symb,sec,sec2,interval);
		System.out.println(dq);
//		System.out.println("https://query1.finance.yahoo.com/v7/finance/download/TSLA?period1=1607627307&period2=1639163307&interval=1d&events=history&includeAdjustedClose=true");
		try {
			Table table = Table.read().usingOptions(CsvReadOptions.builder(new URL(dq)));
			System.out.println(table);
			Table table2 = Table.read().usingOptions(CsvReadOptions.builder(new URL("https://query1.finance.yahoo.com/v7/finance/download/%5EIXIC?period1=1607629770&period2=1639165770&interval=1d&events=history&includeAdjustedClose=true")));
			System.out.println(table2);
		}catch (Exception e){
			e.printStackTrace();
		}*/
		//System.out.printf("The date is %s Time: %s \n seconds: %d",d1,t1,sec);//+seconds);
	}

/*        @Bean
        @Primary
        public ObjectMapper defaultMapper(Jackson2ObjectMapperBuilder builder) {
                ObjectMapper objectMapper = builder.createXmlMapper(false).build();
                objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                return objectMapper;
        }*/

}
