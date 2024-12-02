package top.medicine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.medicine.dto.RespResult;
import top.medicine.entity.Post;
import top.medicine.entity.PostReply;

@RestController
@RequestMapping("post")
@Tag(name = "帖子",description = "帖子相关操作")
public class PostController extends BaseController<Post> {
    //获取频道id下所有的帖子列表
    @Operation(summary = "子帖子列表",
            description = "获取频道id下所有的帖子列表",
            parameters = {
                    @Parameter(name = "id", description = "频道id")
            })
    @PostMapping("list")
    public Object list(@RequestParam("id") Integer channelId) {
        return RespResult.success("OK", postService.query(Post.builder().channelId(channelId).build()));
    }

    @Operation(summary = "回复",
            description = "为某个频道下的帖子追加一个回复",
            parameters = {
                    @Parameter(name = "id", description = "频道id"),
                    @Parameter(name = "message", description = "消息")
            })
    @PostMapping("reply")
    public Object postReply(@RequestParam("id") Integer post_id,@RequestParam("message") String message) {
        if (loginUser == null) {
            return RespResult.fail("未登录");
        }

        if (postService.get(post_id) == null) {
            return RespResult.fail("帖子不存在");
        }
        return RespResult.success("OK",postReplyService.save(
                PostReply.builder()
                        .userId(loginUser.getId())
                        .postId(post_id)
                        .message(message)
                        .build()
        ));

    }
    @Operation(summary = "发布频道",
            description = "为某个频道追加一个提诶",
            parameters = {
                    @Parameter(name = "id", description = "频道id"),
                    @Parameter(name = "title", description = "标题"),
                    @Parameter(name = "message", description = "消息")
            })
    @PostMapping("publish")
    public Object publish(@RequestParam("id") Integer channel_id, @RequestParam("title") String title, @RequestParam("message") String message) {
        if (loginUser == null) {
            return RespResult.fail("未登录");
        }

        if (channelService.get(channel_id) == null) {
            return RespResult.fail("频道不存在");
        }

        Post p = Post.builder().userId(loginUser.getId()).channelId(channel_id).title(title).description(message.substring(0, Math.min(100, message.length()))).build();
        p = postService.save(p);

        return RespResult.success("发布成功",p);
    }
    @Operation(summary = "帖子详情",
            description = "查看一个帖子的详情",
            parameters = {
                    @Parameter(name = "id", description = "频道id"),
            })
    @PostMapping("details")
    public Object details(@RequestParam("id") Integer postId) {
        if (postService.get(postId) == null) {
            return RespResult.fail("帖子不存在");
        }

        return RespResult.success("OK", postReplyService.query(PostReply.builder().postId(postId).build()));
    }
}
