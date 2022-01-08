import io.restassured.response.Response;
import model.objects.pet.Pet;
import model.request.Category;
import model.request.TagsItem;
import org.junit.jupiter.api.*;
import service.PetStoreService;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCrudOnPet {

    PetStoreService petStoreService = new PetStoreService("https://petstore.swagger.io/v2");
    static Pet pet;

    @BeforeAll
    public static void initializePet(){

        pet = new Pet();

        Category category = new Category();
        category.setName("Dog");
        category.setId(1);

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("photo_1.cdn");
        photoUrls.add("photo_2.cdn");

        List<TagsItem> tagsItems = new ArrayList<>();
        TagsItem tagItem = new TagsItem();
        tagItem.setId(123);
        tagItem.setName("Dogs");
        tagsItems.add(tagItem);

        pet.setId(1);
        pet.setName("Karabas");
        pet.setStatus("1");
        pet.setCategory(category);
        pet.setPhotoUrls(photoUrls);
        pet.setTags(tagsItems);

    }

    @Test
    @Order(1)
    public void createPet(){

        Response response = petStoreService.createNewPet(pet);
        System.out.println(response.body().asString());

        assertEquals(200, response.getStatusCode(), "Not successfull!");
        assertEquals(Integer.valueOf(pet.getId()), response.jsonPath().get("id"), "Pet id is not set correctly!");
        assertEquals(pet.getName(), response.jsonPath().get("name"), "Pet name is not set correctly!");
        assertEquals(pet.getStatus(), response.jsonPath().get("status"), "Pet status is not set correctly!");
        assertEquals(Integer.valueOf(pet.getCategory().getId()), response.jsonPath().get("category.id"), "Pet category id is not set correctly!");

    }

    @Test
    @Order(2)
    public void getPetById(){

        Response response = petStoreService.getPetById(String.valueOf(pet.getId()));
        System.out.println(response.body().asString());
        assertEquals(200, response.getStatusCode(), "Not successfull!");

    }

    @Test
    @Order(3)
    public void updatePet(){

        pet.setId(1);
        pet.setName("Karabas_updated");

        Response response = petStoreService.updatePet(pet);
        System.out.println(response.body().asString());

        assertEquals(200, response.getStatusCode(), "Not successfull!");
        assertEquals(pet.getName(), response.jsonPath().get("name"), "Pet is not updated correctly!");

    }

    @Test
    @Order(4)
    public void deletePet(){

        Response loginResponse = petStoreService.login("1","1");
        String apiKey = loginResponse.jsonPath().get("message").toString().substring(23) ;

        Response response = petStoreService.deletePet(apiKey, pet.getId());
        System.out.println(response.body().asString());

        assertEquals(200, response.getStatusCode(), "Not successfull!");

    }

}
