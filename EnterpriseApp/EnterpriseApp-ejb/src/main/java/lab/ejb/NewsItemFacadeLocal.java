package lab.ejb;

import jakarta.ejb.Local;
import lab.ejp.NewsItem;

import java.util.List;

@Local
public interface NewsItemFacadeLocal {
    public List<NewsItem> getAllNewsItems();
}
