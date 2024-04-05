package lab.ejb;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lab.ejp.NewsItem;

@JMSDestinationDefinition(name = "java:app/jms/NewsQueue",
        interfaceName = "jakarta.jms.Queue", resourceAdapter = "jmsra",
        destinationName = "NewsQueue")
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName =
                "destinationLookup", propertyValue = "java:app/jms/NewsQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "jakarta.jms.Queue")
})
public class NewsMDB implements jakarta.jms.MessageListener {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void onMessage(Message message) {
        //ObjectMessage msg = null;
        TextMessage msg = null;
        try {
            if (message instanceof TextMessage) {
                // msg = (ObjectMessage) message;
                // NewsItem e = (NewsItem) msg.getObject();
                msg = (TextMessage) message;
                NewsItem e = new NewsItem();
                e.setHeading(msg.getText().split("\\|")[0]);
                e.setBody(msg.getText().split("\\|")[1]);
                em.persist(e);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
