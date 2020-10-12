package aima.gui.demo.search;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.*;
import aima.core.search.agent.SearchAgent;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.local.SimulatedAnnealingSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;

import java.util.List;
import java.util.Properties;

/**
 * @author Ravi Mohan
 * @author Ruediger Lunde
 * 
 */

public class EightPuzzleDemo {
	private static EightPuzzleBoard boardWithThreeMoveSolution = new EightPuzzleBoard(
			new int[] { 1, 2, 5, 3, 4, 0, 6, 7, 8 });

	private static EightPuzzleBoard random1 = new EightPuzzleBoard(new int[] { 1, 4, 2, 7, 5, 8, 3, 0, 6 });

//	private static EightPuzzleBoard extreme =
//			new EightPuzzleBoard(new int[] { 0, 8, 7, 6, 5, 4, 3, 2, 1 });

	private static EightPuzzleBoard initial = 
		//	new EightPuzzleBoard(new int[] { 1, 4, 2, 7, 5, 8, 3, 0, 6 });
	 //new EightPuzzleBoard(new int[] { 1, 2, 5, 3, 4, 0, 6, 7, 8 });
	 //new EightPuzzleBoard(new int[] { 0, 8, 7, 6, 5, 4, 3, 2, 1 });

	// 20 Movimientos
	// new EightPuzzleBoard(new int[] { 6,2,7,4,5,1,0,8,3 });
	// new EightPuzzleBoard(new int[] {4,7,2,1,0,6,3,5,8});
	//new EightPuzzleBoard(new int[] {7,1,5,4,0,8,2,6,3});
	//new EightPuzzleBoard(new int[] {5,1,6,4,0,3,8,7,2});
	 new EightPuzzleBoard(new int[] {2,4,0,6,3,1,7,8,5});

	// 30 Movimientos
	// new EightPuzzleBoard(new int[] { 5,6,7,2,8,4,0,3,1 });
	public static void main(String[] args) {
		System.out.println("Initial State:\n" + initial);
		System.out.println("Goal  State:\n" + EightPuzzleFunctions.GOAL_STATE);
		// eightPuzzleDLSDemo();
		// eightPuzzleIDLSDemo();
		// eightPuzzleGreedyBestFirstDemo();
		// eightPuzzleGreedyBestFirstManhattanDemo();
		// eightPuzzleAStarDemo();
		// eightPuzzleAStarManhattanDemo();
		// eightPuzzleSimulatedAnnealingDemo();

		// Practica 2:
		eightPuzzleAStarNullHeristicDemo(); //h0
	//eightPuzzleAStarWeigthedMisplaceDemo();//h1
	//eightPuzzleAStarWeightedManhattanDemo();//h2
		//eightPuzzleAStarNonConsistentHeuristicDemo(); //h3
		//eightPuzzleAStarWeigthedNonConsistentHeuristicDemo(); //h3-2
		eightPuzzleAStarEpsilonWeightedManhattanDistanceDemo(); //h4
	}
	
	private static void eightPuzzleAStarNullHeristicDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search(MissplacedTileHeuristic) (9)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initial);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::nullHeuristic);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private static void eightPuzzleAStarWeigthedMisplaceDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search(MissplacedTileHeuristic) (9)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initial);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getWeigthedOfMisplacedTiles);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleAStarWeightedManhattanDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search (ManhattanHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getWeightedManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void eightPuzzleAStarNonConsistentHeuristicDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search (ManhattanHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::nonConsistentHeuristic);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void eightPuzzleAStarWeigthedNonConsistentHeuristicDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search (ManhattanHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::weigthedNonConsistentHeuristic);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void eightPuzzleAStarEpsilonWeightedManhattanDistanceDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search(MissplacedTileHeuristic) (9)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initial);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getEpsilonWeightedManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleDLSDemo() {
		System.out.println("\nEightPuzzleDemo recursive DLS (9)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(boardWithThreeMoveSolution);
			SearchForActions<EightPuzzleBoard, Action> search = new DepthLimitedSearch<>(9);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleIDLSDemo() {
		System.out.println("\nEightPuzzleDemo Iterative DLS");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			SearchForActions<EightPuzzleBoard, Action> search = new IterativeDeepeningSearch<>();
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleGreedyBestFirstDemo() {
		System.out.println("\nEightPuzzleDemo Greedy Best First Search (MisplacedTileHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(boardWithThreeMoveSolution);
			SearchForActions<EightPuzzleBoard, Action> search = new GreedyBestFirstSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getNumberOfMisplacedTiles);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleGreedyBestFirstManhattanDemo() {
		System.out.println("\nEightPuzzleDemo Greedy Best First Search (ManhattanHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(boardWithThreeMoveSolution);
			SearchForActions<EightPuzzleBoard, Action> search = new GreedyBestFirstSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void eightPuzzleAStarDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search (MisplacedTileHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getNumberOfMisplacedTiles);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleSimulatedAnnealingDemo() {
		System.out.println("\nEightPuzzleDemo Simulated Annealing Search");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(boardWithThreeMoveSolution);
			SimulatedAnnealingSearch<EightPuzzleBoard, Action> search = new SimulatedAnnealingSearch<>(
					EightPuzzleFunctions::getManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			System.out.println("Final State:\n" + search.getLastState());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleAStarManhattanDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search (ManhattanHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions::getManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printInstrumentation(Properties properties) {
		properties.keySet().stream().map(key -> key + "=" + properties.get(key)).forEach(System.out::println);
	}

	private static void printActions(List<Action> actions) {
		actions.forEach(System.out::println);
		System.out.println("Number of Actions: " + actions.size()); // Ejercicio 4
	}
}