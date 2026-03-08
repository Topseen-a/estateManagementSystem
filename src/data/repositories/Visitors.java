package data.repositories;

import data.models.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Visitors implements VisitorRepo {

    private List<Visitor> visitors = new ArrayList<>();
    private int nextId = 1;

    @Override
    public List<Visitor> findAll() {
        return new ArrayList<>(visitors);
    }

    @Override
    public int count() {
        return visitors.size();
    }

    @Override
    public Visitor findById(int id) {
        for (Visitor visitor : visitors) {
            if (visitor.getId() == id) {
                return visitor;
            }
        }
        return null;
    }

    @Override
    public Visitor save(Visitor visitor) {

        if (visitor == null) {
            throw new IllegalArgumentException("Visitor cannot be null");
        }

        if (visitor.getId() == 0) {
            visitor.setId(nextId++);
            visitors.add(visitor);
        } else {

            Visitor existingVisitor = findById(visitor.getId());

            if (existingVisitor != null) {
                int index = visitors.indexOf(existingVisitor);
                visitors.set(index, visitor);
            } else {
                visitors.add(visitor);
            }
        }
        return visitor;
    }

    @Override
    public void delete(Visitor visitor) {
        if (visitor != null) {
            visitors.remove(visitor);
        }
    }

    @Override
    public void deleteById(int id) {

        Visitor visitor = findById(id);

        if (visitor != null) {
            visitors.remove(visitor);
        }
    }

    @Override
    public void deleteAll() {
        visitors.clear();
    }
}