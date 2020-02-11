from django.contrib.auth.models import User, Group
from rest_framework import viewsets
from src.quickstart.serializers import UserSerializer, GroupSerializer

class UserViewSet(viewsets.ModelViewSet):
    """
    API Endpoint for User view and edit
    """
    queryset = User.objects.all().order_by('-date_joined')
    serializer_class = UserSerializer


class GroupViewSet(viewsets.ModelViewSet):
    """
    API Endpoint for Group view and edit
    """
    queryset = Group.objects.all()
    serializer_class = GroupSerializer
