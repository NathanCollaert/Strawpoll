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
                    new Strawpoll("Does this question have a lot of answers?",DateTime.Now, new List<Answer>(){ new Answer("Yes",50),new Answer("No",8),  new Answer("Yes",50), new Answer("Yes",50), new Answer("Yes",50), new Answer("Yes",50), new Answer("Yes",50), new Answer("Yes",50), new Answer("Yes",50), new Answer("Yes",50), new Answer("Yes",50), new Answer("Yes",50), new Answer("Yes",50), new Answer("Yes",50), new Answer("Yes",50), new Answer("Yes",50), new Answer("Yes",50), new Answer("Yes",50), new Answer("Yes",50),}),
                    new Strawpoll("How big am I?",DateTime.Now, new List<Answer>(){ new Answer("1m95",5),new Answer("1m92",8),new Answer("1m98",150),new Answer("1m96",500) }),
                    new Strawpoll("How much do I weight?",DateTime.Now, new List<Answer>(){ new Answer("84",5),new Answer("87",8),new Answer("89",150),new Answer("85",500) }),
                    new Strawpoll("What is my favorite sport?",DateTime.Now, new List<Answer>(){ new Answer("Voetbal",5),new Answer("Tennis",8),new Answer("Krachtbal",150),new Answer("Basketbal",500) }),
                    new Strawpoll("Hoe noem ik?",DateTime.Now, new List<Answer>(){ new Answer("Jos",5),new Answer("Piet",8),new Answer("Jan",150),new Answer("Nathan",500) }),
                    new Strawpoll("Wat eet ik het liefst?",DateTime.Now, new List<Answer>(){ new Answer("Pizza",5),new Answer("Burgers",8),new Answer("Spaghetti",150),new Answer("Frieten",500) }),
                    new Strawpoll("Op welke school zit ik?",DateTime.Now, new List<Answer>(){ new Answer("Sivibu",5),new Answer("VHTI",8),new Answer("Uni Gent",150),new Answer("Hogent",500) }),
                    new Strawpoll("Wat is mijn favoriete kleur?",DateTime.Now, new List<Answer>(){ new Answer("Geel",5),new Answer("Groen",8),new Answer("Rood",150),new Answer("Blauw",500) }, new List<VotedUUID>(){new VotedUUID("test") }),
                    new Strawpoll("Dit is een zeer lange vraag maar kun je ze lezen?",DateTime.Now, new List<Answer>(){ new Answer("Ja",5),new Answer("Nee",980) }),
                    new Strawpoll("Heeft deze vraag een zeer lang antwoord?",DateTime.Now, new List<Answer>(){ new Answer("Ja, deze vraag heeft het langste antwoord van alle vragen",50000),new Answer("Nee",980) })
                };

                strawpolls.ForEach(e => _ctx.Strawpolls.Add(e));
            }

            _ctx.SaveChanges();
        }

    }
}
