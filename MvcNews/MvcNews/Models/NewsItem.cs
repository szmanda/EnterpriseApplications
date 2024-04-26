using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace MvcNews.Models
{
    public class NewsItem
    {
        public int Id { get; set; }
        [DataType(DataType.Date)]
        public DateTime Timestamp { get; set; }
        [Required]
        [DisplayName("News content")]
        [StringLength(140, MinimumLength=5, ErrorMessage="Wrong length")]
        public string Text { get; set; } = string.Empty;

        [Timestamp]
        public byte[] RowVersion { get; set; } = new byte[0];

    }
}
