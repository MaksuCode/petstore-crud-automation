package client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestAssuredClient {

    String baseUrl ;
    RequestSpecification reqSpec;

    protected RestAssuredClient(String baseUrl){
        this.baseUrl = baseUrl ;
        this.reqSpec = new RequestSpecBuilder()
                .setUrlEncodingEnabled(false)
                .setContentType(ContentType.JSON)
                .setBaseUri(baseUrl)
                .log(LogDetail.ALL)
                .build() ;
    }

    protected Response get(String path){
        return
                given()
                        .spec(reqSpec)
                        .get(baseUrl + path);
    }

    protected Response post(String path , Object object){
        return
                given()
                        .spec(reqSpec)
                        .body(object)
                        .post(baseUrl.concat(path));
    }

    protected Response getWithParam(String path , String paramName , String value){
        return
                given()
                        .spec(reqSpec)
                        .param(paramName , value)
                        .get(path) ;
    }

    protected Response getWithParam(String path , String paramName1 , String value1 , String paramName2, String value2){
        return
                given()
                        .spec(reqSpec)
                        .param(paramName1, value1)
                        .param(paramName2, value2)
                        .get(path) ;
    }

    protected Response delete(String path , String apiKey){
        return
                given()
                        .spec(reqSpec)
                        .header("api_key" , apiKey)
                        .delete(path) ;
    }

    protected Response put(String path , Object object){
        return
                given()
                        .spec(reqSpec)
                        .body(object)
                        .put(baseUrl.concat(path)) ;
    }

}
