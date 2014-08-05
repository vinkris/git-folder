#include<iostream>
#include<queue>
#include<vector>
#include<algorithm>
#include<numeric>
#include<time.h>
#include<iomanip>

using namespace std;

#define all(v) (v).begin(), (v).end()

struct Task
{
   int timestamp;
   int pages;

   Task(int timestamp, int pages)
   {
      this->timestamp = timestamp;
      this->pages = pages;
   }

   int waitTime(int currTime)
   {
       return currTime-timestamp;
   }

};

struct Printer
{
   int ppm;
   Task *task;
   int timeRemaining;

   Printer(int ppm)
   {
      this->ppm = ppm;
      this->task = NULL;
      this->timeRemaining = 0;
   }

   bool isBusy()
   {
      return this->task != NULL;
   }

   void tick()
   {
      if (isBusy())
      {
          timeRemaining -= 1;
          if (timeRemaining <= 0)
             task = NULL;
      }

   }

   void startNext(Task *task)
   {
       this->task = task;
       this->timeRemaining = task->pages * 60/this->ppm;
   }
   
};

void simulation(int seconds, int students, int tasksperstudentperhour, int ppm, int avgPages)
{
   Printer printer(ppm);
   queue<Task> Q;
   vector<int> waitingTimes;   
   int secsPerTask = 3600/(students*tasksperstudentperhour);

   for(int i = 0; i < seconds; i++)
   {
      if( ((rand()%secsPerTask)+1) == secsPerTask)
      {
          Q.push(Task(i, rand()%(avgPages+1)));
      }

      if (!printer.isBusy() && !Q.empty())
      {
          Task *task = &Q.front();
          waitingTimes.push_back(task->waitTime(i));
          printer.startNext(task);
          Q.pop();
      }
      
      printer.tick();
   }

   cout<< "Average waiting times for tasks is " << setprecision(4) << accumulate(all(waitingTimes), 0)/double(waitingTimes.size()) << " and " << Q.size() << " tasks remaining" << endl;

}

int main(void)
{
  
  srand(time(NULL));
  for ( int i = 0 ; i < 10 ; i++ )
  {
     simulation(3600, 10, 2, 5, 20);
  }
  return 0;
}
