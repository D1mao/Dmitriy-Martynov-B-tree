package BTree;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    private static Integer[] generateRandomNumbers(int size) {
        Random random = new Random();
        Integer[] numbers = new Integer[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = random.nextInt();
        }
        return numbers;
    }
    public static void main(String[] args) {
        BTree bTree = new BTree(4);
        Integer[] randomNumbers = generateRandomNumbers(10000);

        double totalInsertOperations = 0;
        double totalSearchOperations = 0;
        double totalDeleteOperations = 0;

        double totalInsertTime = 0;
        double totalSearchTime = 0;
        double totalDeleteTime = 0;

        ArrayList<Long> insertOperationsList = new ArrayList<>();
        ArrayList<Long> searchOperationList = new ArrayList<>();
        ArrayList<Long> deleteOperationList = new ArrayList<>();

        for (int i = 0; i < randomNumbers.length; i++) {
            long startTime = System.nanoTime();
            bTree.insert(randomNumbers[i]);
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;

            insertOperationsList.add(bTree.getInsertOperations());
            totalInsertTime += elapsedTime;
            totalInsertOperations += bTree.getInsertOperations();
            System.out.printf("Добавлено число %d: Итераций - %d, Время - %d нс%n", randomNumbers[i], insertOperationsList.get(i), elapsedTime);

            bTree.resetInsertOperations();
        }

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Integer target = randomNumbers[random.nextInt(randomNumbers.length)];
            long startTime = System.nanoTime();
            bTree.search(target);
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            totalSearchTime += elapsedTime;
            totalSearchOperations += bTree.getSearchOperations();
            searchOperationList.add(bTree.getSearchOperations());

            System.out.printf("Найдено число %d: Итераций - %d, Время - %d нс%n", target, bTree.getSearchOperations(), elapsedTime);

            bTree.resetSearchOperations();
        }

        for (int i = 0; i < 1000; i++) {
            Integer target = randomNumbers[random.nextInt(randomNumbers.length)];
            long startTime = System.nanoTime();
            bTree.delete(target);
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            totalDeleteTime += elapsedTime;
            totalDeleteOperations += bTree.getDeleteOperations();
            deleteOperationList.add(bTree.getDeleteOperations());

            System.out.printf("Удалено число %d: Итераций - %d, Время - %d нс%n", target, bTree.getDeleteOperations(), elapsedTime);

            bTree.resetDeleteOperations();
        }

        System.out.println("Среднее количество операций вставки: " + (totalInsertOperations / randomNumbers.length));
        System.out.println("Среднее количество операций поиска: " + (totalSearchOperations / 100));
        System.out.println("Среднее количество операций удаления: " + (totalDeleteOperations / 1000));
        System.out.println("Среднее время вставки: " + (totalInsertTime / randomNumbers.length) + " нс");
        System.out.println("Среднее время поиска: " + (totalSearchTime / 100) + " нс");
        System.out.println("Среднее время удаления: " + (totalDeleteTime / 1000) + " нс");
    }
}