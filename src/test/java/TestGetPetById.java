import io.restassured.response.Response;
import model.objects.pet.Pet;
import model.request.Category;
import model.request.TagsItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import service.PetStoreService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetPetById {

    static PetStoreService petStoreService = new PetStoreService("https://petstore.swagger.io/v2");
    static Pet pet ;

    @BeforeAll
    public static void createPet(){

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

        petStoreService.createNewPet(pet);

    }

    @Test
    public void happyPath(){

        Response response = petStoreService.getPetById(String.valueOf(pet.getId()));
        System.out.println(response.body().asString());
        assertEquals(200, response.getStatusCode(), "Not successfull!");

    }

    @ParameterizedTest
    @MethodSource("provideInvalidParametersForTest")
    public void getPetByIdTest(String id, int expectedStatusCode){

        Response response = petStoreService.getPetById(id);

        System.out.println(response.body().asString());

        assertEquals(expectedStatusCode , response.statusCode());

    }

    private static Stream<Arguments> provideInvalidParametersForTest() {
        return Stream.of(
                Arguments.of("-12345", 404),
                Arguments.of("", 405),
                Arguments.of("abcd", 405)
        );
    }

}
