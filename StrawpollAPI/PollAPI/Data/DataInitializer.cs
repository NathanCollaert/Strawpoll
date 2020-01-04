using PollAPI.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PollAPI.Data
{
    public class DataInitializer
    {
        private readonly PollContext _ctx;

        public DataInitializer(PollContext ctx)
        {
            _ctx = ctx;
        }

        public void InitializeData()
        {
            if (_ctx.Database.EnsureDeleted())
            {
                _ctx.Database.EnsureCreated();

                List<Strawpoll> strawpolls = new List<Strawpoll>()
                {
                    new Strawpoll("How many children has Queen Elizabeth the Second got?",DateTime.Now, new List<Answer>(){ new Answer("1",20),new Answer("2",10),  new Answer("3",12), new Answer("4",9), new Answer("5",15), new Answer("6",30)}),
                    new Strawpoll("What colour is a panda?",DateTime.Now, new List<Answer>(){ new Answer("Yellow and white",9),new Answer("Pink and white",12),new Answer("Blue and white",15),new Answer("Gold and blue",23),new Answer("Black and white",48) }),
                    new Strawpoll("What language has the most words?",DateTime.Now, new List<Answer>(){ new Answer("Dutch",5),new Answer("French",8),new Answer("German",150),new Answer("Chinese",500), new Answer("English", 25) }),
                    new Strawpoll("What temperature does water boil at?",DateTime.Now, new List<Answer>(){ new Answer("50",0),new Answer("100",150),new Answer("30",0),new Answer("20",0), new Answer("180",20) }),
                    new Strawpoll("Where was Lope De Vega born?",DateTime.Now, new List<Answer>(){ new Answer("Rome",50),new Answer("Amsterdam",8),new Answer("Paris",150),new Answer("Berlin",240), new Answer("Madrid",800) }),
                    new Strawpoll("When did the first World War start?",DateTime.Now, new List<Answer>(){ new Answer("1914",50),new Answer("1915",10),new Answer("1917",5),new Answer("1916",18) }),
                    new Strawpoll("What's the most important book in the Islam?",DateTime.Now, new List<Answer>(){ new Answer("Bible",500),new Answer("Torah",800),new Answer("Lord Of The Rings",0),new Answer("Harry Potter",200), new Answer("Koran",8551) }),
                    new Strawpoll("What's the capital of Honduras",DateTime.Now, new List<Answer>(){ new Answer("Tegucigalpa",5),new Answer("Paris",8),new Answer("Sydney",150),new Answer("Rome",500), new Answer("Madrid",8) }),
                    new Strawpoll("What did the crocodile swallow in 'Peter Pan'?",DateTime.Now, new List<Answer>(){ new Answer("An alarm clock",1705),new Answer("Tinkerbell",520) , new Answer("Captain Hook's hook",355), new Answer("A pen",237),new Answer("A gauntlet",94)}),
                    new Strawpoll("What's the best known artificial international language?",DateTime.Now, new List<Answer>(){ new Answer("Dutch",200),new Answer("Esperanto",980) })
                };

                strawpolls.ForEach(e => _ctx.Strawpolls.Add(e));
            }

            _ctx.SaveChanges();
        }

    }
}
