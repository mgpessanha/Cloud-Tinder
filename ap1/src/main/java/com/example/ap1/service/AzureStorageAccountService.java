package com.example.ap1.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

@Service
public class AzureStorageAccountService {

    public String uploadFileToAzure(MultipartFile file) throws IOException {
        String connectionString = "DefaultEndpointsProtocol=https;AccountName=ibmecazureblobmg;AccountKey=MfQ4AzV7PVPOTLMW4oKN/PPO8cKCH6gTO4ex1G6mKG+43HmiAMlt3U8rvNpMGzHGJcznxSfu43zM+AStNhbQtw==;EndpointSuffix=core.windows.net"; // chave de conexão no azure

        BlobContainerClient client = new BlobContainerClientBuilder() // objeto que conecta no azure
            .connectionString(connectionString)
            .containerName("images") // nome da pasta
            .buildClient(); // construção do objeto

        BlobClient blob = client.getBlobClient(file.getOriginalFilename()); // avisa pro azure que vai subir um arquivo com esse nome "file"

        blob.upload(file.getInputStream(), file.getSize(), true); // pega o arquivo, seu tamanho e o true é pra subscrever o arquivo que tiver o mesmo nome e a mesma extensão.

        return "https://ibmecazureblobmg.blob.core.windows.net/images/" + file.getOriginalFilename(); // retorna o endereço do arquivo (a concatenação já coloca o nome do arquivo).

    }
    
}

