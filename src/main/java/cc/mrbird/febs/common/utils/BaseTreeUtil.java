package cc.mrbird.febs.common.utils;

import cc.mrbird.febs.common.entity.BaseTree;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseTreeUtil {

    private static final String TOP_NODE_ID = "0";

    public static <T> BaseTree<T> buildBaseTree(List<BaseTree<T>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<BaseTree<T>> topNodes = new ArrayList<>();
        nodes.forEach(children -> {
            String pid = children.getParentId();
            if (pid == null || TOP_NODE_ID.equals(pid)) {
                topNodes.add(children);
                return;
            }
            for (BaseTree<T> parent : nodes) {
                String id = parent.getId();
                if (id != null && id.equals(pid)) {
                    parent.getChilds().add(children);
                    children.setHasParent(true);
                    parent.setHasChild(true);
                    return;
                }
            }
        });

        BaseTree<T> root = new BaseTree<>();
        root.setId(TOP_NODE_ID);
        root.setParentId(StringUtils.EMPTY);
        root.setHasParent(false);
        root.setHasChild(true);
        root.setChecked(true);
        root.setChilds(topNodes);
        Map<String, Object> state = new HashMap<>(16);
        root.setState(state);
        return root;
    }


    public static <T> List<BaseTree<T>> buildList(List<BaseTree<T>> nodes, String idParam) {
        if (nodes == null) {
            return new ArrayList<>();
        }
        List<BaseTree<T>> topNodes = new ArrayList<>();
        nodes.forEach(children -> {
            String pid = children.getParentId();
            if (pid == null || idParam.equals(pid)) {
                topNodes.add(children);
                return;
            }
            nodes.forEach(parent -> {
                String id = parent.getId();
                if (id != null && id.equals(pid)) {
                    parent.getChilds().add(children);
                    children.setHasParent(true);
                    parent.setHasChild(true);
                }
            });
        });
        return topNodes;
    }
}
