package elasticsearch.app;

import java.util.Arrays;
import org.junit.Test;

import org.elasticsearch.client.Client;

//Test
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import elasticsearch.app.ESMain;

/**
 * @author ooleon
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ESMainTest {

	@Test
	public void test(){
        try {
            ESMain.app();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println( ESMain.test() );
	}
}
