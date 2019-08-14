   
   public class ObjectPool<T> where T : new()
    {
        private static List<T> _available = new List<T>();
        private List<T> _inUse = new List<T>();
 
        private int counter = 0;
        private int MAXTotalObjects;
 
        private static ObjectPool<T> instance = null;
 
        public static ObjectPool<T> GetInstance()
        {
            if (instance == null)
            {
                instance = new ObjectPool<T>();
            }
            else
            {
                Console.WriteLine("This is singleton!");
            }
            return instance;
        }
 
        public T acquireReusable()
        {
            if (_available.Count != 0 && _available.Count < MAXTotalObjects)
            {
                T item = _available[0];
                _inUse.Add(item);
                _available.RemoveAt(0);
                counter--;
                return item;
            }
            else
            {
                T obj = new T();
                _inUse.Add(obj);
                return obj;
            }
        }
 
        public void ReleaseReusable(T item)
        {
            if (counter <= MAXTotalObjects)
            {
                _available.Add(item);
                counter++;
                _inUse.Remove(item);
            }
            else
            {
                Console.WriteLine("To much object in pool!");
            }
        }
 
        public void SetMaxPoolSize(int settingPoolSize)
        {
            MAXTotalObjects = settingPoolSize;
        }
    }
 

    class PooledObject
    {
        DateTime _createdAt = DateTime.Now;
 
        public DateTime CreatedAt
        {
            get { return _createdAt; }
        } 
    }

  class Program
    {
        public 	static void Main(string[] args)
        {
            ObjectPool<PooledObject> objPool = ObjectPool<PooledObject>.GetInstance();
            objPool.SetMaxPoolSize(10);
            PooledObject obj = objPool.acquireReusable();
            objPool.ReleaseReusable(obj);
            Console.WriteLine(obj.CreatedAt);
            Console.ReadKey();
        }
    }


