package com.cantarino.kafkademo.controllers;

import com.cantarino.kafkademo.Models.Mensagem;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

      private KafkaTemplate<String, String> KafkaTemplate;
      private Gson jsonConverter;

      @Autowired
      public KafkaController(KafkaTemplate<String, String> KafkaTemplate , Gson jsonConverter)
      {
           this.KafkaTemplate = KafkaTemplate;
           this.jsonConverter = jsonConverter;
      }

      @PostMapping
      public void post(@RequestBody Mensagem mensagem)
      {
         this.KafkaTemplate.send("cantarino", jsonConverter.toJson(mensagem));
      }

      @KafkaListener(topics = "cantarino" , groupId = "myGroupId")
      public void getFromKafka(String mensagem)
      {
           Mensagem msg = jsonConverter.fromJson(mensagem , Mensagem.class);
            String result = new StringBuilder(500).append(msg.getNome()).append(" - ").append(msg.getSobreNome()).toString();
           System.out.println(result);
      }
}
