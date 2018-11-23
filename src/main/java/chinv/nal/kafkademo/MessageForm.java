package chinv.nal.kafkademo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MessageForm implements Serializable
{
	private String topicName;
	private String content;
}
