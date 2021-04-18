using System.ComponentModel.DataAnnotations;


namespace EAD2CA2_BirdAppAPI.Models
{
    public class Birds
    {
        [Key]
        public int id { get; set; }

        //[Required]
        public string commonName { get; set; }
        //[Required]
        public string binomial { get; set; }
        //[Required]
        public string irishName { get; set; }
        //[Required]
        public string orderName { get; set; }
        //[Required]
        public string familyName { get; set; }
        //[Required]
        public string infoLink { get; set; }
        //[Required]
        public byte liked { get; set; }
        //[Required]
        public string imageLink { get; set; }
    }
}
