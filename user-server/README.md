java -javaagent:/Users/jayjeon/Documents/pinpoint-agent-2.5.3/pinpoint-bootstrap-2.5.1.jar \
-Dpinpoint.agentId=user-agent-id \
-Dpinpoint.applicationName=userapp \
-Dpinpoint.config=/Users/jayjeon/Documents/pinpoint-agent-2.5.1/pinpoint-root.config \
-jar /Users/jayjeon/IdeaProjects/demo/build/libs/demo-0.0.1-SNAPSHOT.jar