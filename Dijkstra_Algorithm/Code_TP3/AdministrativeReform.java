import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AdministrativeReform {
	private boolean[] selected;
	private int[] distance1;
	private int[] distance2;
	private Queue<Connection> connected;
	private int first, second, any;
	private List<Connection>[] nodes;
	private int cap1, cap2;

	@SuppressWarnings("unchecked")
	public AdministrativeReform(int numNodes, int numEdges){
		nodes = new LinkedList[numNodes];
		for(int i=0; i<nodes.length; i++){
			nodes[i]= new LinkedList<Connection>();
		}
		selected = new boolean[numNodes];
		distance1 = new int[numNodes];
		distance2 = new int[numNodes];
		connected = new PriorityQueue<Connection>(numEdges, new Comparator<Connection>() {
			public int compare(Connection arg0, Connection arg1) {
				if (arg0.cost < arg1.cost)
					return -1;
				else if (arg0.cost > arg1.cost)
					return 1;
				else
					return 0;
			}
		});
	}

	private void init(int origin) {
		if (origin == cap1) {
			for (int i = 0; i < distance1.length; i++) {
				distance1[i] = Integer.MAX_VALUE;
				selected[i] = false;
			}
			distance1[origin] = 0;

		} else if (origin == cap2) {
			for (int i = 0; i < distance1.length; i++) {
				distance2[i] = Integer.MAX_VALUE;
				selected[i] = false;
			}
			distance2[origin] = 0;

		}
		connected.offer(new Connection(origin, 0));
	}

	public void addConnection(int from, int to, int cost){
		nodes[from].add(new Connection(to, cost));
		nodes[to].add(new Connection(from, cost));
	}
	
	public void setCapitals(int cap1, int cap2){
		this.cap1=cap1; this.cap2=cap2;
	}

	public void computeSolution() {
		init(cap1);
		int node;
		do {
			node = connected.remove().getNode();
			selected[node] = true;
			exploreNode(node,cap1);
		} while (!connected.isEmpty()); 
		init(cap2);
		do {
			node = connected.remove().getNode();
			selected[node] = true;
			exploreNode(node, cap2);
		} while (!connected.isEmpty()); 
		
		for(int j=0; j<distance1.length; j++){
			if(distance1[j]<distance2[j])
				first++;
			else if(distance1[j]>distance2[j])
				second++;
			else
				any++;
		}
	}

	public int getFirst(){
		return first;
	}
	public int getSecond(){
		return second;
	}
	public int getAny(){
		return any;
	}

	private void exploreNode(int node,int cap) {
		int length;
		if(cap==cap1){
		for (Connection c : nodes[node]) {
			if (!selected[c.node]) {
				length = distance1[node] + c.cost;
				if (length < distance1[c.getNode()]) {
					boolean nodeIsInQueue = distance1[c.node] < Integer.MAX_VALUE;
					distance1[c.node] = length;
					if (nodeIsInQueue) {
						Connection explored = new Connection(c.node, length);
						connected.remove(explored);
						connected.offer(explored);
					} else {
						connected.offer(new Connection(c.node, distance1[c.node]));
					}
				}
			}
		}
		}else{
		for (Connection c : nodes[node]) {
			if (!selected[c.node]) {
				length = distance2[node] + c.cost;
				if (length < distance2[c.getNode()]) {
					boolean nodeIsInQueue = distance2[c.node] < Integer.MAX_VALUE;
					distance2[c.node] = length;
					if (nodeIsInQueue) {
						Connection explored = new Connection(c.node, length);
						connected.remove(explored);
						connected.offer(explored);
					} else {
						connected.offer(new Connection(c.node, distance2[c.node]));
					}
				}
			}
		}}
	}

	private class Connection{
		int node,cost;
		public Connection(int node, int cost) {
			this.node=node; this.cost=cost;
		}
		public int getNode(){ return node;}
		@Override
		public boolean equals(Object other){
			return ((Connection)other).node == this.node;
		}
	}
}
