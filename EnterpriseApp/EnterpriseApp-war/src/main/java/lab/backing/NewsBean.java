package lab.backing;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.ObjectMessage;
import jakarta.jms.TextMessage;
import lab.ejb.NewsItemFacadeLocal;
import lab.ejp.NewsItem;

import java.util.List;

@RequestScoped
@Named
public class NewsBean {
    @Inject
    private NewsItemFacadeLocal facade;
    @Inject
    private JMSContext context;
    @Resource(lookup="java:app/jms/NewsQueue")
    private jakarta.jms.Queue queue;
    private String headingText;
    private String bodyText;

    public String getHeadingText() {
        return headingText;
    }

    public void setHeadingText(String headingText) {
        this.headingText = headingText;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public String submitNews() {
        this.sendNewsItem(headingText, bodyText);
        return null;
    }

    void sendNewsItem(String heading, String body) {
        try {
            //ObjectMessage message = context.createObjectMessage();
            TextMessage message = context.createTextMessage();
            NewsItem e = new NewsItem();
            e.setHeading(heading);
            e.setBody(body);
            //message.setObject(e);
            String textContent = heading + '|' + body;
            message.setText(textContent);
            context.createProducer().send(queue, message);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    public List<NewsItem> getNewsItems() {
        return facade.getAllNewsItems();
    }

}
