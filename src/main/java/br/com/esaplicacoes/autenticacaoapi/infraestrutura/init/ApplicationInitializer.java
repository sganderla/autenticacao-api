package br.com.esaplicacoes.autenticacaoapi.infraestrutura.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 28/05/2022
 * @since 1.0.0
 */
@Slf4j
@Service
public class ApplicationInitializer {

    private List<InitialTask> tasks;

    /**
     *
     *
     * @param tasks
     */
    public ApplicationInitializer(List<InitialTask> tasks) {
        this.tasks = tasks;
    }

    /**
     *
     */
    @PostConstruct
    public void orderTasks() {
        this.tasks.sort(AnnotationAwareOrderComparator.INSTANCE);
    }

    /**
     *
     * @param event
     */
    @EventListener
    public void onApplicationStart(ContextRefreshedEvent event) {
        this.tasks.forEach(InitialTask::perform);
    }
}