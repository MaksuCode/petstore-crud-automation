package service;

import client.RestAssuredClient;
import io.restassured.response.Response;
import model.objects.pet.Pet;
import model.request.CreateOrUpdatePetRequestBody;

/**
 * PetStore API class that holds the endpoint methods to be used in test classes.
 */
public class PetStoreService extends RestAssuredClient {


    public PetStoreService(String baseUrl) {
        super(baseUrl);
    }

    public Response login(String username, String password){
        return getWithParam("/user/login", "username", username, "password", password);
    }

    public Response createNewPet(Pet pet){

        CreateOrUpdatePetRequestBody createOrUpdatePetRequestBody = new CreateOrUpdatePetRequestBody();
        createOrUpdatePetRequestBody.setId(pet.getId());
        createOrUpdatePetRequestBody.setName(pet.getName());
        createOrUpdatePetRequestBody.setStatus(pet.getStatus());

        createOrUpdatePetRequestBody.setCategory(pet.getCategory());
        createOrUpdatePetRequestBody.setPhotoUrls(pet.getPhotoUrls());
        createOrUpdatePetRequestBody.setTags(pet.getTags());

        return post("/pet", createOrUpdatePetRequestBody);

    }

    public Response getPetById(String id){
        return get("/pet/" + id);
    }

    public Response updatePet(Pet pet){
        CreateOrUpdatePetRequestBody createOrUpdatePetRequestBody = new CreateOrUpdatePetRequestBody();
        createOrUpdatePetRequestBody.setId(pet.getId());
        createOrUpdatePetRequestBody.setName(pet.getName());
        createOrUpdatePetRequestBody.setStatus(pet.getStatus());

        createOrUpdatePetRequestBody.setCategory(pet.getCategory());
        createOrUpdatePetRequestBody.setPhotoUrls(pet.getPhotoUrls());
        createOrUpdatePetRequestBody.setTags(pet.getTags());

        return put("/pet", createOrUpdatePetRequestBody);
    }

    public Response deletePet(String apiKey, int petId){
        return delete("/pet/" + petId, apiKey);
    }

}
