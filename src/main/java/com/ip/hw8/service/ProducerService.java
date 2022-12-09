package com.ip.hw8.service;

import com.ip.hw8.entity.Producer;
import com.ip.hw8.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducerService {
    private final ProducerRepository producerRepository;

    public List<Producer> findAll() {
        return producerRepository.findAll();
    }

    public Producer findByName(String name) {
        return producerRepository.findByName(name);
    }

    public void createProducer(String producerName) {
        Producer producer = new Producer();
        producer.setName(producerName);
        producerRepository.save(producer);
    }

    public void updateProducer(Long producerId, String producerName) {
        Producer producer = new Producer();
        producer.setId(producerId);
        producer.setName(producerName);

        producerRepository.save(producer);
    }

    public void deleteProducer(Long producerId) {
        producerRepository.deleteById(producerId);
    }
}
