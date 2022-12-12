package br.com.oliveiralex.springbatchv5;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class BatchConfig {
	
	@Autowired
	private GithubClient githubClient;
	
	List<String> listGithub = Arrays.asList(
			"oliveiralex", "acenelio", "devsuperior", "Paulocesar90", "marcotuliosr",
			"douglasfabiano1", "luizdaviDL", "lleandrroo", "easbarba", "edulongodevgeo",
			"Retr0981", "yurijivago", "cleomarcio2019", "luisinho", "tonistorres",
			"marciorbarcellos", "CassianoCardoso", "eliseubrito", "AdailSilva", "yasssuz",
			"Pablo-Henrique", "raldineyr", "vitorvd", "fSantosLima", "jorgecrodrigues",
			"lucianodiisouza", "andrezasecon", "MarceloMachadoxD", "Henrique-Moreira",
			"johnymbr");
	
	@Bean
	public Job job(JobRepository jobRepository, Step step) {
		return new JobBuilder("job", jobRepository).start(step).build();
	}

	@Bean
	public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("step", jobRepository)
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
					System.out.println("Olá, mundo");
					int i = 1;
					
					for (String userProfile: listGithub) {
						Mono<UserResponse> data = githubClient.findUserByName(userProfile);
						UserResponse user = data.toFuture().get();
						log.info("[{}]: id: {} , login: {}, name: {}, location: {}", i, user.getId(), user.getLogin(), user.getName(), user.getLocation());
						i++;
					}
					
					return RepeatStatus.FINISHED;
				}, transactionManager).build();
	}
}
